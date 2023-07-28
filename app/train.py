import joblib
import logging
import pandas as pd
import numpy as np

from sklearn.ensemble import RandomForestRegressor
from sklearn.feature_extraction.text import TfidfVectorizer

logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.INFO)


def process_dataframe(X: pd.DataFrame):
    # get one hot encodings for categorical variables
    X_ohe = pd.get_dummies(X.drop(['title', 'skills'], axis=1))
    pd.DataFrame(X_ohe.columns).to_csv('data/categorical_structure.csv')
    logging.info('Saved dataframe structure for categorical variables')

    # get sparse matrix for text features
    vectorizer = TfidfVectorizer()
    skills_matrix = vectorizer.fit_transform(X.skills)
    joblib.dump(vectorizer, 'model/skills_matrix_vectorizer.pkl')
    logging.info('Saved TfIdfVectorizer model for skills column')
    title_matrix = vectorizer.fit_transform(X.title)
    joblib.dump(vectorizer, 'model/title_matrix_vectorizer.pkl')
    logging.info('Saved TfIdfVectorizer model for title column')
    text_sparse_matrix = np.hstack((skills_matrix.toarray(), title_matrix.toarray()))

    return pd.DataFrame(np.hstack((X_ohe, text_sparse_matrix)))


def fit(X_train, y_from_train, y_to_train):
    rf = RandomForestRegressor(n_estimators=300, n_jobs=-1, random_state=13)
    rf.fit(X_train, y_from_train)
    logging.info('Model to predict y_from has been trained')
    joblib.dump(rf, "model/random_forest_from.joblib")
    logging.info('Saved model for y_from prediction')
    rf.fit(X_train, y_to_train)
    logging.info('Model to predict y_to has been trained')
    joblib.dump(rf, "model/random_forest_to.joblib")
    logging.info('Saved model for y_to prediction')


if __name__ == "__main__":
    df = pd.read_csv('data/train/data.csv')
    X, y_from, y_to = process_dataframe(df.drop(['from', 'to'], axis=1)), df['from'], df['to']
    fit(X, y_from, y_to)
