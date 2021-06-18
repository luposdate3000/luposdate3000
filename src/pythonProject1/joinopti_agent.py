import gym
import gym_database
import socket


import numpy as np

from stable_baselines3.common.noise import NormalActionNoise, OrnsteinUhlenbeckActionNoise
from stable_baselines3 import DQN, PPO, DDPG


# create socket server

host = '127.0.0.1'
port = 60000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind((host, port))
    s.listen()
    conn, addr = s.accept()
    with conn:
        print('Connected by', addr)
        while True:
            data = conn.recv(1024)
            print(repr(data))
            if not data:
                break
            if data.decode("UTF-8") == "optimize":
                conn.sendall(b'start')
            elif data.decode("UTF-8") == "learn":
                env = gym.make('gym_database:Database-v0')
                env.set_connection(conn)
                model = PPO("MlpPolicy", env, verbose=2)
                model.learn(total_timesteps=30000, log_interval=1)
                conn.sendall(b'done_learning')

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
