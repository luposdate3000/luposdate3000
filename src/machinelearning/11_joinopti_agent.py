#!/usr/bin/env python
import gym
import gym_database
import socket
from datetime import date
import sys
import numpy as np
from stable_baselines3 import DQN, PPO, DDPG, A2C
from stable_baselines3.common.env_util import make_vec_env
import math

N_JOIN_ORDERS = 15


def train_model():
    benched_queries = read_query(benched_query_file)
    
    # find min max execution times
    max_execution_time = max_ex_t(benched_queries)
    min_execution_time = min_ex_t(benched_queries)
    # find max id of predicate
    max_dict_id = max_id(benched_queries)
    # setup environment
    env = gym.make('gym_database:Database-v0')
    #env = make_vec_env('gym_database:Database-v0')
    env.set_observation_space(max_dict_id)
    env.set_max_exec_t(max_execution_time)
    env.set_min_exec_t(min_execution_time)
    # setup model
    model = PPO("MlpPolicy", env, verbose=2)
    #model = PPO("MlpPolicy", env, verbose=2)
    #model = DQN("MlpPolicy", env, verbose=2)
    #model = A2C("MlpPolicy", env, verbose=0)
    #print(model)

    # for i in range(len(benched_queries)):
    # for i in range(21):
    #     env.set_training_data([benched_queries[i]])
    #     model.learn(total_timesteps=50000, log_interval=1)
    # model.save(benched_query_file + "." + str(date.today()) + ".ppo_model")
    # env = model.get_env()
    # del model
    # model = PPO.load("ppo_gym_database")
    # env = model.get_env()

    env.set_training_data(benched_queries)
    model.learn(total_timesteps=25000000, log_interval=None)
    # model.save(benched_query_file + "." + str(date.today()) + ".ppo_model")
    #model.save("train.me.s.50k" + ".ppo_model")
    model.save("train.me.s.25000k" + "3:7_4_triples" + ".ppo_model")



def optimize_query():
    benched_queries = read_query(query_file)
    # find min max execution times
    max_execution_time = max_ex_t(benched_queries)
    min_execution_time = min_ex_t(benched_queries)
    # find max id of predicate
    max_dict_id = max_id(benched_queries)
    # setup environment
    env = gym.make('gym_database:Database-v0')
    env.set_observation_space(max_dict_id)
    env.set_max_exec_t(max_execution_time)
    env.set_min_exec_t(min_execution_time)
    # setup model
    model = PPO.load(optimizer_model_file)
    #model = DQN.load(optimizer_model_file)
    #model = A2C.load(optimizer_model_file)

    rewards = []
    actions = []
    query_counter = 0
    env.set_training_data(benched_queries)
    for i in range(len(benched_queries)):
        done = False
        print("---------------Query: ----------- " + str(query_counter))
        obs = env.reset()
        print("Observation: ")
        print(obs)
        while not done:
            actions.append([])
            action, _states = model.predict(obs, deterministic=True)
            print(f"Action: {action}")
            obs, reward, done, info = env.step(action)
            actions[query_counter].append(action)
            print("Observation: ")
            print(obs)
            print(f"Reward: {reward}")
            print(f"Done: {done}")
            print(info)
            if reward < -10: done = True
            print("---------------Query: ----------- " + str(query_counter))
            if done:
                query_counter += 1
                print("finish")
                rewards.append(reward)

    for i in rewards:
        print(i)

    with open("evaluation." + optimizer_model_file, "w") as evaluation:
        evaluation.write(str(max_execution_time) + "\n")
        evaluation.write(str(min_execution_time) + "\n")
        for i in range(len(benched_queries)):
            evaluation.write(str(rewards[i]) + " ")
            for j in range(3):
                evaluation.write(str(-(math.sqrt(abs(float(benched_queries[i][j][2]) - max_execution_time)) /
                                       math.sqrt(max_execution_time - min_execution_time) * 10)))
                if j != 2:
                    evaluation.write(" ")
                else:
                    evaluation.write("\n")


def analyse():
    return None


def read_query(q_file):
    benched_queries = []
    with open(q_file, "r") as p_file:
        counter = 0  # all join orders of one query
        counter2 = 0  # index for one query
        for line in p_file:
            # if new query
            if counter == 0:
                tmp = line.split(" ")
                tmp[-1] = tmp[-1][:-1]  # cut off "\n"
                # create new list for the query and all its join orders
                benched_queries.append([tmp])
            else:
                tmp = line.split(" ")
                tmp[-1] = tmp[-1][:-1]  # cut off "\n"
                # add join order of query #counter2 to list
                benched_queries[counter2].append(tmp)

            if counter == N_JOIN_ORDERS-1:
                counter = 0
                counter2 += 1
            else:
                counter += 1
    print("warnke")
    print(benched_queries[0])
    print("warnke")
    print(benched_queries)
    return benched_queries


def max_ex_t(benched_q):
    tmp = []
    for i in benched_q:
        for j in range(0, 3):
            tmp.append(float(i[j][2]))
    return max(tmp)


def min_ex_t(benched_q):
    tmp = []
    for i in benched_q:
        for j in range(0, 3):
            tmp.append(float(i[j][2]))
    return min(tmp)


def max_id(benched_q):
    tmp = []
    for i in benched_q:
        for j in range(len(i)):
            tmp.append(int(i[j][0].split(";")[2].split(",")[1]))
    return max(tmp)


if __name__ == '__main__':

    try:
        train_or_opti = sys.argv[1]
    except:
        print("Param 1: \"train\" or \"opti\" (without \")")
        print("Param 2: train: full path for input file")
        sys.exit()

    if train_or_opti == "train":
        try:
            benched_query_file = sys.argv[2]
        except:
            print("Param 2: train: full path to benched query input file")
            sys.exit()
        train_model()

    elif train_or_opti == "opti":
        try:
            query_file = sys.argv[2]
            optimizer_model_file = sys.argv[3]
        except:
            print("Param 2: opti: full path to query input file")
            print("Param 3: opti: full path to file of trained optimizer (e.g. ../../model.ppo)")
            sys.exit()
        optimize_query()

    else:
        print("Param 1: \"train\" or \"opti\" (without \")")
        print("Param 2: train: full path for input file")











# create socket server

# host = '127.0.0.1'
# port = 60000
#
# with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
#     s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
#     s.bind((host, port))
#     s.listen()
#     conn, addr = s.accept()
#     with conn:
#         print('Connected by', addr)
#         while True:
#             data = conn.recv(1024)
#             print(repr(data))
#             if not data:
#                 break
#             if data.decode("UTF-8") == "optimize":
#                 conn.sendall(b'start')
#             elif data.decode("UTF-8") == "learn":
#                 env = gym.make('gym_database:Database-v0')
#                 env.set_connection(conn)
#                 model = PPO("MlpPolicy", env, verbose=2)
#                 model.learn(total_timesteps=30000, log_interval=1)
#                 conn.sendall(b'done_learning')

# #env.observation_space
# #n_actions = env.action_space.shape[-1]
# #action_noise = NormalActionNoise(mean=np.zeros(n_actions), sigma=0.1 * np.ones(n_actions))

# #model.save("ppo_gym_database")
# #env = model.get_env()
# #del model
# #model = PPO.load("ppo_gym_database")
# #env = model.get_env()
#
#
# obs = env.reset()
# while True:
#     # env.env_method("DatabaseEnv.tell_a_story()")
#     #env.env.env_method('tell_a_story')
#     #env.env_method()
#
#     #env.set_connection(conn)
#     print("Observation: ")
#     print(obs)
#     action, _states = model.predict(obs, deterministic=True)
#     print(f"Action: {action}")
#     obs, reward, done, info = env.step(action)
#     print(f"Action taken: {info}")
#     print("Observation: ")
#     print(obs)
#     # env.render()
#     # print(obs)
#     print(f"Reward: {reward}")
#     print(f"Done: {done}")
#     # print(info)
#     if done:
#         obs = env.reset()
