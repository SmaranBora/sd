////////////////////////////Server.py//////////////////////////////////

import socket
import time

# Lists to store client sockets and their times
client_sockets = []
client_times = []

def start_server():
    host = '127.0.0.1'
    port = 8080

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((host, port))
    server_socket.listen(5)

    print("Server started. Waiting for 3 clients to connect...")

    # Accept 3 clients first
    for i in range(3):
        conn, addr = server_socket.accept()
        print(f"Client {i+1} connected from {addr}")
        client_sockets.append(conn)

    # Receive time from each client
    for conn in client_sockets:
        client_time = float(conn.recv(1024).decode())
        client_times.append(client_time)
        print(f"Received client time: {client_time:.2f}")

    # Server's own time
    server_time = time.time()
    print(f"Server (Master) time: {server_time:.2f}")

    # Calculate average
    all_times = client_times + [server_time]
    average_time = sum(all_times) / len(all_times)
    print(f"Calculated average synchronized time: {average_time:.2f}")

    # Send the same synchronized time to all clients
    for conn in client_sockets:
        conn.send(str(average_time).encode())
        conn.close()

    print("Synchronized time sent to all clients. Server done.")

if __name__ == '__main__':
    start_server()


  //////////////////////////////client.py/////////////////////////////////////////////
  import socket
import time

def start_client():
    host = '127.0.0.1'  # Server address (localhost)
    port = 8080  # Port where the server is listening

    # Create a client socket
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((host, port))  # Connect to the server

    # Send the current local time to the server
    local_time = time.time()
    client_socket.send(str(local_time).encode())  # Send time as a string
    print(f"Sent client time: {local_time:.2f}")

    # Receive the synchronized time from the server
    synchronized_time = client_socket.recv(1024).decode()  # Receive response
    print(f"Received synchronized time: {synchronized_time}")

    # Close the client socket
    client_socket.close()

if __name__ == '__main__':
    start_client()
/////////////////////////////////////////////////////////////////////////////////////////////////////
  how to run
  python or python3 server.py
  python client.py -3 times
  
