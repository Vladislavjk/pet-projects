import requests
import pandas as pd
import json
import logging
import warnings

logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.INFO)
warnings.filterwarnings('ignore')

url = 'https://justjoin.it/api/offers'

drop_columns = [
    'street',
    'address_text',
    'company_url',
    'country_code',
    'latitude',
    'longitude',
    'published_at',
    'employment_types',
    'open_to_hire_ukrainians',
    'id',
    'display_offer',
    'company_logo_url',
    'remote_interview',
    'remote',
    'multilocation',
    'currency',
    'way_of_apply',
    'company_size'
]


def load_json(load_url):
    response = requests.get(load_url)
    assert 200 == response.status_code
    return response.json()


def create_dataframe_from_json(json_data):
    return pd.json_normalize(json_data)


def process_dataframe(df: pd.DataFrame):
    # remove non-PL jobs
    df = df[df.country_code == 'PL']

    logging.info('Removed non-PL jobs')

    # process employment types
    new_df = pd.DataFrame(columns=df.columns)
    for i, employment_types in enumerate(df.employment_types):
        for x in employment_types:
            try:
                temp = df[i:i + 1]
                employment_dict = json.loads(str(x).replace('\'', '"'))
                temp.loc[:, ["contract_type", "from", "to", "currency"]] = [employment_dict['type'],
                                                                            employment_dict['salary']['from'],
                                                                            employment_dict['salary']['to'],
                                                                            employment_dict['salary']['currency']]
                new_df = new_df.append(temp)
            except:
                pass

    logging.info('Processed employment types')

    # process skills
    new_df.skills = [' '.join([skill['name'] for skill in skills]).lower() for skills in new_df.skills]

    logging.info('Processed skills')

    # process title
    new_df.title = [title.lower() for title in new_df.title]

    logging.info('Processed title')

    # remove non-pln currency
    new_df = new_df[new_df['currency'] == 'pln']

    logging.info('Removed non-pln currency')

    return new_df.drop(drop_columns, axis=1)


if __name__ == "__main__":
    data = load_json(url)
    dataframe = create_dataframe_from_json(data)
    dataframe = process_dataframe(dataframe)
    dataframe.to_csv('data/train/data.csv', index=False)
