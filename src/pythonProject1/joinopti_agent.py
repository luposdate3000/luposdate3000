import gym
import gym_database
import socket
import sys

import numpy as np

from stable_baselines3.common.noise import NormalActionNoise, OrnsteinUhlenbeckActionNoise
from stable_baselines3 import DQN, PPO, DDPG

N_JOIN_ORDERS = 3


def train_model():
    benched_queries = []
    with open(param_file, "r") as p_file:
        counter = 0  # all join orders of one query
        counter2 = 0  # index for one query
        for line in p_file:
            if counter == 0:
                tmp = line.split(" ")
                tmp[-1] = tmp[-1][:-1]
                benched_queries.append([tmp])
            else:
                tmp = line.split(" ")
                tmp[-1] = tmp[-1][:-1]
                benched_queries[counter2].append(tmp)

            if counter == N_JOIN_ORDERS-1:
                counter = 0
                counter2 += 1
            else:
                counter += 1

    env = gym.make('gym_database:Database-v0')
    model = PPO("MlpPolicy", env, verbose=2)
    # for i in range(len(benched_queries)):
    for i in range(21):
        env.set_training_data([benched_queries[i]])
        model.learn(total_timesteps=50000, log_interval=1)
    model.save(param_file + ".ppo_model")
        # env = model.get_env()
        # del model
        # model = PPO.load("ppo_gym_database")
        # env = model.get_env()



def optimize_query():
    env = gym.make('gym_database:Database-v0')
    model = PPO.load(param_file)
    benched_queries = []
    with open(param_file2, "r") as p_file:
        counter = 0  # all join orders of one query
        counter2 = 0  # index for one query
        for line in p_file:
            if counter == 0:
                tmp = line.split(" ")
                tmp[-1] = tmp[-1][:-1]
                benched_queries.append([tmp])
            else:
                tmp = line.split(" ")
                tmp[-1] = tmp[-1][:-1]
                benched_queries[counter2].append(tmp)

            if counter == N_JOIN_ORDERS-1:
                counter = 0
                counter2 += 1
            else:
                counter += 1


    for i in range(21):
        print("---------------Query: ----------- " + str(i))
        env.set_training_data([benched_queries[i]])
        obs = env.reset()
        print("Observation: ")
        print(obs)
        action, _states = model.predict(obs, deterministic=True)
        print(f"Action: {action}")
        obs, reward, done, info = env.step(action)
#     print(f"Action taken: {info}")
        print("Observation: ")
#     print(obs)
#     # env.render()
        print(obs)
#     print(f"Reward: {reward}")
#     print(f"Done: {done}")
#     # print(info)
        if done:
            obs = env.reset()
            print("Observation: ")
            print(obs)
    

if __name__ == '__main__':

    try:
        train_or_opti = sys.argv[1]
    except:
        print("Param 1: \"train\" or \"opti\" (without \")")
        print("Param 2: train: full path for input file")
        sys.exit()

    if train_or_opti == "train":
        try:
            param_file = sys.argv[2]
        except:
            print("Param 2: train: full path for input file")
            sys.exit()
        train_model()

    elif train_or_opti == "opti":
        try:
            param_file = sys.argv[2]
            param_file2 = sys.argv[3]
        except:
            print("Param 2: train: full path for input file")
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
