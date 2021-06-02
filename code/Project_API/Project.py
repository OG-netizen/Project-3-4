from os import name
import mysql.connector
from flask import Flask,request,render_template
# mydb = mysql.connector.connect(host ="sql11.freesqldatabase.com",user ="sql11416191",passwd ="uGIUwBp9CN")

# mycursor=mydb.cursor()

# mycursor.execute("show databases")

# for db in mycursor:
#     print(db)

# mycursor.execute("SELECT * FROM sql11416191.Accounts")

# myresult = mycursor.fetchall()

# for x in myresult:
#   print(x)

mydb = mysql.connector.connect(host ="sql11.freesqldatabase.com",user ="sql11416191",passwd ="uGIUwBp9CN")

mycursorUser=mydb.cursor()
mycursorUser.execute("SELECT Username FROM sql11416191.Accounts")
myresult = mycursorUser.fetchall()

mycursorPassword=mydb.cursor()
mycursorPassword.execute("SELECT Password FROM sql11416191.Accounts")
mypassword = mycursorPassword.fetchall()

mycursorBalance=mydb.cursor()
# mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'JiaHax'")
myBalance= 0



# if request.form['username'] == 'JiaHax':
#         mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'JiaHax'")
#         myBalance = mycursorBalance.fetchall()
# elif request.form['username'] == 'NoobMaster':  
#         mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'NoobMaster'")   
#         myBalance = mycursorBalance.fetchall()

nameList =[]
passList = []

for items in mypassword:
  passList.append(items[0])

for items in myresult:
  nameList.append(items[0])

app = Flask(__name__)



@app.route("/")
def home():
    return render_template("login.html")

@app.route('/form_login',methods=['POST','GET'])
def login():
    if user():
      if password():
        return render_template('home.html',name =request.form['username'],content = execute())
      else:
        return render_template('login.html',info='Password Incorrect')
    else:
      return render_template('login.html',info='Invalid User')

def user():
   if request.form['username'] in nameList:
     return True
   else:
     print(nameList)
     return False
       

def password():
    if request.form['password'] in passList:
     return True
    else:
      print
      return False
   

def execute():
    if request.form['username'] == 'JiaHax':
        mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'JiaHax'")
        return mycursorBalance.fetchall()
    elif request.form['username'] == 'NoobMaster':
        mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'NoobMaster'")
        return mycursorBalance.fetchall()
    elif request.form['username'] == 'Regex':
        mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'Regex'")
        return mycursorBalance.fetchall()
    elif request.form['username'] == 'Pewdiepie':
        mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'Pewdiepie'")
        return mycursorBalance.fetchall()


if __name__ == "__main__":
    app.run(debug=True)



# while True:

#   mycursorUser=mydb.cursor()
#   mycursorUser.execute("SELECT Username FROM sql11416191.Accounts")
#   myresult = mycursorUser.fetchall()

#   mycursorPassword=mydb.cursor()
#   mycursorPassword.execute("SELECT Password FROM sql11416191.Accounts")
#   mypassword = mycursorPassword.fetchall()

#   mycursorBalance=mydb.cursor()
#   # mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'JiaHax'")
#   myBalance= 0



    




  
