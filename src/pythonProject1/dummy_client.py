import socket

HOST = '127.0.0.1'  # The server's hostname or IP address
PORT = 60000        # The port used by the server

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    s.sendall(b'train')
    # create a training set and send one set to join after another
    out_list = ["-1,13,212;171,2,-1;98,7,-1", "-1,13,12;11,2,-1;9,7,-1"]
    for out in out_list:
        done = False
        while not done:
            data = s.recv(1024)
            if data.decode("UTF-8") == "start" or data.decode("UTF-8") == "redo":
                s.sendall(str(len(out)).encode("UTF-8") + out.encode("UTF-8"))
                data = s.recv(1024)
                # build graph out of data and reassemble operator graph
                # nodes = split(data, ;)
                # edges = edges from nodes(node)
                # calculate reward/costs
                reward = -3
                s.sendall(str(reward).encode("UTF-8"))
            elif data.decode("UTF-8") == "done":
                done = True
    s.sendall(b'stop')

    s.sendall(b'optimize')
    data = s.recv(1024)
    print('Received', data)
    if data.decode("UTF-8") == "start":
        # TODO: function to convert triple ids to string
        # out = triples_to_String()
        out = "-1,13,212;171,2,-1;98,7,-1"
        s.sendall(str(len(out)).encode("UTF-8") + out.encode("UTF-8"))
        data = s.recv(1024)
        # build graph out of data and reassemble operator graph
        # nodes = split(data, ;)
        # edges = edges from nodes(node)
    # elif:
    #     data.decode("UTF-8") == ""


#print('Received', repr(data))