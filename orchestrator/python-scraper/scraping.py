import requests
from bs4 import BeautifulSoup
import json
import argparse


parser = argparse.ArgumentParser()
parser.add_argument("--asin", required=True)

parser.add_argument("--url", required=True)



args = parser.parse_args()
asin = args.asin
url = args.url + asin



HEADERS = ({'User-Agent':
			'Mozilla/5.0 (Windows NT 10.0; Win64; x64) \
			AppleWebKit/537.36 (KHTML, like Gecko) \
			Chrome/90.0.4430.212 Safari/537.36',
			'Accept-Language': 'en-US, en;q=0.5',
      'referer':'{url}'})



allow = requests.get(url,headers=HEADERS)


soup = BeautifulSoup(allow.text,'lxml')

 
review_texts = [
    i.text.strip().replace('\n', ' ')
    for i in soup.find_all("div", {'data-hook': "review-collapsed"})
]

reviews = []

for idx, text in enumerate(review_texts):
    review = {
        "id": idx + 1,
        "text": text,
        "source": "AMAZON"
    }

    reviews.append(review)

# Scrape The Review Texts / Comments
# Remove All The Extras Using Strip And Replace Methods
for i in soup.findAll("div",{'data-hook':"review-collapsed"}): # the tag which is common for all the names of products
    review_texts.append(i.text.strip('\n').replace('\n                \n\n\n\n\xa0','').
                        replace('The media could not be loaded.','').lstrip(' '))
 

payload = {
    "reviews": reviews
}

print(json.dumps(payload, ensure_ascii=False, indent=2))