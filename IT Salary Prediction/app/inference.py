import pandas as pd
import numpy as np
import joblib
import warnings

warnings.filterwarnings('ignore')


def process_dataframe(X_test: pd.DataFrame):

    # Handle categorical features

    categorical_structure = pd.read_csv('data/categorical_structure.csv')
    # create a dataframe with the same structure as in training
    X_ohe = pd.DataFrame(columns=categorical_structure['0'].values)
    # add data from test dataframe
    X_ohe = X_ohe.append(pd.get_dummies(X_test.drop(['title', 'skills'], axis=1))).fillna(0)

    last_column_index_in_training_structure = X_ohe.columns.get_loc(categorical_structure['0'].values[-1])
    # delete redundant columns to save same structure as in training
    X_ohe.drop(X_ohe.iloc[:, last_column_index_in_training_structure + 1:], inplace=True, axis=1)

    # Handle text features

    title_vectorizer = joblib.load('model/title_matrix_vectorizer.pkl')
    skills_vectorizer = joblib.load('model/skills_matrix_vectorizer.pkl')
    title_vocabulary = title_vectorizer.get_feature_names_out()
    skills_vocabulary = skills_vectorizer.get_feature_names_out()

    # normalize skills and title columns
    X_test.title = [' '.join([word.lower() for word in str(title).split() if word.lower() in title_vocabulary]) for title in
                    X_test.title]
    X_test.skills = [' '.join([word.lower() for word in str(skills).split() if word.lower() in skills_vocabulary]) for skills
                     in X_test.skills]

    # create sparse matrices using trained vectorizers
    title_matrix = title_vectorizer.transform(X_test.title)
    skills_matrix = skills_vectorizer.transform(X_test.skills)
    text_sparse_matrix = np.hstack((skills_matrix.toarray(), title_matrix.toarray()))

    return pd.DataFrame(np.hstack((X_ohe, text_sparse_matrix)))


def predict_from_to_salaries(X_test: pd.DataFrame):
    rf_from = joblib.load('model/random_forest_from.joblib')
    rf_to = joblib.load('model/random_forest_to.joblib')
    y_pred_from = rf_from.predict(X_test)
    y_pred_to = rf_to.predict(X_test)
    return pd.DataFrame({'from': y_pred_from, 'to': y_pred_to})


if __name__ == "__main__":
    X_test = process_dataframe(pd.read_csv('data/test/test.csv'))
    print(predict_from_to_salaries(X_test))

