from collections import Counter
import matplotlib.pyplot as plt
import networkx as nx
import sys
import time
import itertools
import pymysql
import re
from TwitterAPI import TwitterAPI

consumer_key = 'aqWQzq1T0xLrSfxJHD7FhvJgF'
consumer_secret = '1gFIH1TGGFfJG9YxizUau2KBguV84Xds0IZwJ0saL20JIduU91'
access_token = '769178823468408832-jnMZxvAWvcKWZeVrMvWoPnzPa8SekEn'
access_token_secret = 'YPPt5Y2gIrscrqbul3jp9H1YPRR2As4XE34abQK6IXDfh'

cnx = pymysql.connect(user='root', password='anand123', host='localhost', database='myDatabase')
cursor = cnx.cursor(pymysql.cursors.DictCursor)





def get_twitter():
    """ 
    Returns:
      An instance of TwitterAPI.
    """
    return TwitterAPI(consumer_key, consumer_secret, access_token, access_token_secret)


def get_timeline():
    """ 
    Returns:
      timeline.
    """
    twitter = get_twitter()
    screen_name = 'BestBuy_Deals'
    request = robust_request(twitter, 'statuses/user_timeline', {'screen_name' : screen_name, 'count' : 200})
    timeline = request.json()
    return timeline



def getProductNameList():
    """ 
    Returns:
      List of Products.
    """
    query = ("select * from product_table;")
    cursor.execute(query)
    listOfProducts = []
    for product in cursor.fetchall():
        listOfProducts.append(product['product_name'])
    return listOfProducts

def getMatchedProducts():

    dealMatchedGauranteed = []
    productsMatched = set()
    listOfProducts = getProductNameList()
    timeline = get_timeline()
    for product in listOfProducts:
        for tweet in timeline:
            deal = (tweet['text']).encode('ascii','ignore')
            if product in deal.decode("utf-8").replace('\n',' '):
                # print(deal)
                # print(product)
                # print([deal.decode("utf-8").replace('\n',' ')])
                productsMatched.add(product)
                dealMatchedGauranteed = dealMatchedGauranteed + [deal.decode("utf-8").replace('\n',' ')]
                print(dealMatchedGauranteed)
                print(productsMatched)
    return dealMatchedGauranteed,productsMatched

def robust_request(twitter, resource, params, max_tries=2):
    """ If a Twitter request fails, sleep for 15 minutes.
    Do this at most max_tries times before quitting.
    Args:
      twitter .... A TwitterAPI object.
      resource ... A resource string to request; e.g., "friends/ids"
      params ..... A parameter dict for the request, e.g., to specify
                   parameters like screen_name or count.
      max_tries .. The maximum number of tries to attempt.
    Returns:
      A TwitterResponse object, or None if failed.
    """
    for i in range(max_tries):
        request = twitter.request(resource, params)
        if request.status_code == 200:
            return request
        else:
            print('Got error %s \nsleeping for 15 minutes.' % request.text)
            sys.stderr.flush()
            time.sleep(61 * 15)

def main():
    """ Main method. You should not modify this. """
    twitter = get_twitter()
    listOfProducts = getProductNameList()
    timeline = get_timeline()
    matchedProducts,productsMatched = getMatchedProducts()
    print("matchedProducts ------------: ",matchedProducts)
    file = open("DealMatches.txt", "w")
    for matchedProducts in matchedProducts:
        file.write(matchedProducts+'\n')
    file.close()
    file1 = open("ProductMatches.txt", "w")
    for productMatched in productsMatched:
        file1.write(productMatched+'\n')
    file1.close()

	    
if __name__ == '__main__':
    main()
