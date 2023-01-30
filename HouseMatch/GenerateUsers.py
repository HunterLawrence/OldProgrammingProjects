# -*- coding: utf-8 -*-
"""
Author: Hunter Lawrence
Date: 5/19/2022
File: GenerateUsers.py
"""

import random

# User Format: Name, Age, Education

# possible names list
names = ["Jean", "James", "Allison", "Finn", "Russel", "Billie", "Frankie", "Caden", "Dane", "Owen"]

# open file
file = open("UserData.txt", "w")

for x in range(1000):
    # create a name using two from the name list
    firstName = names[random.randint(0, len(names) - 1)]
    lastName = names[random.randint(0, len(names) - 1)]
    
    name = firstName + " " + lastName
    
    # get a random age between 10 and 100
    age = random.randint(10, 101)
    
    # generate an education up to 8 (but with a 50% of no education)
    education = random.randint(0, 16)
    if(education < 8):
        education = 0
    else:
        education = education - 8
    
    # Write information to file
    file.write(name + "," + str(age) + "," + str(education) + "\n")

file.close()