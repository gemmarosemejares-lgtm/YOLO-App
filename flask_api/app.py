from flask import Flask, request, jsonify
import mysql.connector

app = Flask(__name__)

# MYSQL CONNECTION

conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="yolo_db"
)

cursor = conn.cursor()

# HOME

@app.route("/")
def home():
   return "YOLO Flask API Working!"

# SIGNUP

@app.route("/signup", methods=["POST"])
def signup():

    name = request.form["name"]
    email = request.form["email"]
    password = request.form["password"]

    sql = """
    INSERT INTO users(name,email,password)
    VALUES(%s,%s,%s)
    """

    values = (name, email, password)

    cursor.execute(sql, values)

    conn.commit()

    return jsonify({
        "message": "Signup Success"
    })

# LOGIN

@app.route("/login", methods=["POST"])
def login():

    email = request.form["email"]
    password = request.form["password"]

    sql = """
    SELECT * FROM users
    WHERE email=%s AND password=%s
    """

    values = (email, password)

    cursor.execute(sql, values)

    user = cursor.fetchone()

    if user:

        return jsonify({
            "message": "Login Success"
        })

    else:

        return jsonify({
            "message": "Invalid Account"
        })

# RUN APP

if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True)