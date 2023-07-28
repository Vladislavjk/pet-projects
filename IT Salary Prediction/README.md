# IT salary prediction project using guide

### 1. Clone a repository one of the ways:

#### Using git:
```
git clone https://github.com/Vladislavjk/it-salary-prediction.git
```

#### Download zip and extract it:
[Download archive](https://github.com/Vladislavjk/it-salary-prediction/archive/refs/heads/main.zip)

### 2. Go to the directory with unpacked project
* Example: C:\it-salary-prediction

### 3. Open .env file, put your PostgreSQL database credentials here and save the file

```
DB_USERNAME=
DB_PASSWORD=
DB_NAME=
```

### 4. Create virtual environment

Create a new virtual environment

```
py -m venv venv
```

Activate virtual environment

```
venv\scripts\activate
```

### 5. Install all needed dependencies

```
pip install -r requirements.txt
```

### 6. Run parse.py script

This script parses JustJoinIT data and saves data.csv file in the app/data/train directory

```
python app\parse.py
```

### 7. Run train.py script

This script trains Random Forest and TfIdfVectorizer models and saves them in the app/model directory

```
python app\train.py
```

### 8. Run inference.py script

This script predicts min and max salary for test.csv data in app/data/test directory

```
python app\inference.py
```

### 9. Run Flask Web application

```
flask run -p 5000
```

# Project Stack
* Python
* PostgreSQL
