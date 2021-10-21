import sys

# add gym-database to PYTHONPATH to runtime
sys.path.insert(0, '/home/a/PycharmProjects/pythonProject1/gym-database')

# marked error by pycharm can be ignored
# run "pip install -e ." in /gym-database folder to install environment
from gym_database.envs.database_env import DatabaseEnv
