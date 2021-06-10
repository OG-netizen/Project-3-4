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

mycursorNames = mydb.cursor()
mycursorNames.execute("SELECT FirstNaam FROM sql11416191.Customer")
myNames = mycursorNames.fetchall()



mycursorPassword=mydb.cursor()
mycursorPassword.execute("SELECT Passwords FROM sql11416191.Accounts")
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

realName =[]
nameList =[]
passList = []

for items in myNames:
  realName.append(items[0])

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
        return render_template('home.html',name =executeNames(),content = execute())
      else:
        return render_template('login.html',info='Password Incorrect')
    else:
      return render_template('login.html',info='Invalid User')

@app.route('/form_logout',methods=['POST','GET'])
def submit(): 
	  if request.method == "POST":
		  return render_template("login.html")


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
   

def executeNames():
    if request.form['username'] == 'JiaHax':
      return 'Jia-jie'
    elif request.form['username'] == 'NoobMaster':
         return 'Bryan'
    elif request.form['username'] == 'frikandelb':
        return 'Wouter'
    elif request.form['username'] == 'Mastermast':
        return 'Jurgen'

def execute():
    if request.form['username'] == 'JiaHax':
        mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'JiaHax'")
        return mycursorBalance.fetchall()
    elif request.form['username'] == 'Mastermast':
        mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'Mastermast'")
        return mycursorBalance.fetchall()
    elif request.form['username'] == 'frikandelb':
        mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'frikandelb'")
        return mycursorBalance.fetchall()
    elif request.form['username'] == 'Noobmaster':
        mycursorBalance.execute("SELECT Balance FROM sql11416191.Accounts WHERE Username = 'Noobmaster'")
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



    




  
