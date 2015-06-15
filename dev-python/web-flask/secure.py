from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World!'

if __name__ == '__main__':
    app.run('127.0.0.1', port=5001, ssl_context='adhoc')
    #app.run(ssl_context='adhoc', debug=True)
    #app.run(host='0.0.0.0', port='5001', ssl_context='adhoc')
