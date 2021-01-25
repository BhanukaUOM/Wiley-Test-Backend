import yaml
import os

with open("src/main/resources/application.yml", 'r') as appyml:
    app = yaml.load(appyml)

# Data Source
app['spring']['datasource']['url'] = os.environ['DATASOURCE_URL']
app['spring']['datasource']['username'] = os.environ['DATASOURCE_USERNAME']
app['spring']['datasource']['password'] = os.environ['DATASOURCE_PASSWORD']

# Mail Server
app['spring']['mail']['username'] = os.environ['SMTP_USERNAME']
app['spring']['mail']['password'] = os.environ['SMTP_PASSWORD']

with open("src/main/resources/application.yml", "w") as appyml:
    yaml.dump(app, appyml)