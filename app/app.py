import time
import pandas as pd
import os

from dotenv import load_dotenv
from inference import process_dataframe, predict_from_to_salaries
from flask import Flask, render_template, request, flash, redirect, url_for
from flask_sqlalchemy import SQLAlchemy

load_dotenv()

DB_USERNAME=os.getenv('DB_USERNAME')
DB_PASSWORD=os.getenv('DB_PASSWORD')
DB_NAME=os.getenv('DB_NAME')


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = f'postgresql+psycopg2://{DB_USERNAME}:{DB_PASSWORD}@localhost:5432/{DB_NAME}'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.secret_key = 'vladislav'
db = SQLAlchemy(app)


class Job(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(300), nullable=False)
    city = db.Column(db.String(50), nullable=False)
    marker_icon = db.Column(db.String(50), nullable=False)
    workplace_type = db.Column(db.String(50), nullable=False)
    company_name = db.Column(db.String(50), nullable=False)
    experience_level = db.Column(db.String(50), nullable=False)
    skills = db.Column(db.String(300), nullable=False)
    contract_type = db.Column(db.String(50), nullable=False)
    salary_from = db.Column(db.Float, nullable=False)
    salary_to = db.Column(db.Float, nullable=False)

    def __init__(self, title, city, marker_icon, workplace_type, company_name,
                 experience_level, skills, contract_type, salary_from, salary_to):
        self.title = title
        self.city = city
        self.marker_icon = marker_icon
        self.workplace_type = workplace_type
        self.company_name = company_name
        self.experience_level = experience_level
        self.skills = skills
        self.contract_type = contract_type
        self.salary_from = salary_from
        self.salary_to = salary_to


@app.route('/', methods=['GET', 'POST'])
def home():
    if request.method == 'POST':
        if not request.form['title'] and not request.form['city'] and not request.form['marker_icon'] \
                and not request.form['workplace_type'] and not request.form['company_name'] \
                and not request.form['experience_level'] and not request.form['skills'] \
                and not request.form['contract_type']:
            flash('Please enter some fields', 'error')
        else:

            data = {
            'title': [request.form['title']],
            'city': [request.form['city']],
            'marker_icon': [request.form['marker_icon']],
            'workplace_type': [request.form['workplace_type']],
            'company_name': [request.form['company_name']],
            'experience_level': [request.form['experience_level']],
            'skills': [request.form['skills']],
            'contract_type': [request.form['contract_type']],
            }

            df_test = pd.DataFrame(data)
            y_pred = predict_from_to_salaries(process_dataframe(df_test))

            job = Job(
                    request.form['title'],
                    request.form['city'],
                    request.form['marker_icon'],
                    request.form['workplace_type'],
                    request.form['company_name'],
                    request.form['experience_level'],
                    request.form['skills'],
                    request.form['contract_type'],
                    y_pred['from'][0],
                    y_pred['to'][0])

            db.session.add(job)
            db.session.commit()
            flash('Record was succesfully added')
            return redirect(url_for('home'))
    jobs = Job.query.all()
    job_list = [job.__dict__ for job in jobs]
    df = pd.DataFrame.from_records(job_list).drop('_sa_instance_state', axis=1)
    columns = ['title', 'city', 'marker_icon', 'workplace_type', 'company_name', 'experience_level', 'skills', 'contract_type', 'salary_from', 'salary_to']
    df = df[columns]
    return render_template('index.html', data=df.to_html(classes='centered'))

if __name__ == '__main__':
    dbstatus = False
    while not dbstatus:
        try:
            db.create_all()
        except:
            time.sleep(2)
        else:
            dbstatus = True
    app.run(debug=True, host='0.0.0.0')