import requests
import json

localUrl = 'http://127.0.0.1:8080/queryUserIntentions'
onlineUrl = 'http://122.9.154.255:8080/queryUserIntentions'

data = {
    'custNo': '360428757154252848',
}

headers = {
    'Content-Type': 'application/json;charset=UTF-8'
}

response = requests.post(onlineUrl, data=json.dumps(data), headers=headers)

if response.status_code == 200:
    result = response.json()
    print(result)
else:
    print('Error: ', response.status_code)