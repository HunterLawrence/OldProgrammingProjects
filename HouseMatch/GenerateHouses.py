# -*- coding: utf-8 -*-
"""
Author: Hunter Lawrence
Date: 5/23/2022
File: GenerateHouses.py
"""


# create a list of 200 houses and save to a file
# houses range in price from 10,000 to 100,000,000

import random

streetNames = ["Mediterranean Ave.", "Baltic Ave.", "Oriental Ave.", "Vermot Ave.", "Connecticut Ave.", "St. Charles Pl.",
               "States Ave.", "Vriginia Ave.", "St. James Pl.", "Tennessee Ave.", "New York Ave.", "Kentucky Ave.", "Indiana Ave.",
               "Illinois Ave.", "Atlantic Ave.", "Ventnor Ave.", "Marvin Gdn.", "Pacific Ave.", "North Carolina Ave.", "Pennsylvania Ave.",
               "Park pl.", "Boardwalk st."]

# create file
file = open("houseMarket.txt", "w")

for x in range(200):
    # generate address:
        # random building number between 1 and 1000 (exclusive)
        # random street name from list
    buildingNum = random.randint(1, 999)
    street = random.choice(streetNames)
    
    
    # generate price:
        # number between 1 and 1000 (inclusive)
        # number of zeroes between 4 and 5
    num1 = random.randint(100, 1000)
    num2 = random.randint(2, 3)
    
    price = num1 * (10**num2)
    
    file.write(str(buildingNum) + " " + street + "," + str(price) + "\n")

file.close()