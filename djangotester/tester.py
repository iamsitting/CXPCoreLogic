import requests
import csv
import json
from pprint import pprint

csvfile = open('MOCK_DATA.csv', 'r')

fnames = ('id', 'city', 'code', 'tzone')
reader = csv.DictReader(csvfile, fnames)
out = json.dumps([row for row in reader])
out = out.replace(u'\ufeff','')
print out
#print reader
#with open('MOCK_DATA.json', 'w') as jsonfile:
#	json.dump(out,jsonfile)

headers = {'Content-Type':'application/json'}
#payload = {'title':'hello', 'description':'new world'}
payload = json.dumps(out)
print payload

r = requests.post('http://iamsitting.no-ip.org/api/', json = out,headers=headers)
print r.content
print r.json()
