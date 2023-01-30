# -*- coding: utf-8 -*-
"""
Author: Hunter Lawrence
Date: 5/29/2022
File: HouseMatch.py
"""

import pandas

WASHINGTON_STATE_YEARLY_MINIMUM_SALARY = 30000

# Returns aproximate income of a user
def estimateIncome(user):
    #split user string into parseable values
    userData = user.split(",")
    
    # assign variables from userData
    userAge = int(userData[1])
    userEducation = int(userData[2])
    
    # if the user is below the age of 18, the user is not considered in the market for a house
    if userAge < 18 :
        return 0;
    
    # determine aproximate years working by subtracing 18 from the age as well as years in education and retirement
    
    workingYears = userAge - 18 - userEducation
    if userAge > 75:
        workingYears = workingYears - (userAge - 75)
    
    # starting values are based on washington state yearly minimum wage and is multiplied by an
    # additional 15% per year in education (based on findings form the Bureau of Labor Statistics)
    # as well as an additional 5% per working year (compounded yearly starting at 20 years working)
    estimatedIncome = WASHINGTON_STATE_YEARLY_MINIMUM_SALARY
    estimatedIncome = estimatedIncome * (1 + .15 * userEducation)
    
    if workingYears > 20 :
        estimatedIncome = estimatedIncome * (1.05 ** (workingYears - 20))
    
    # return estimatedIncome
    return estimatedIncome
    

# determines the aproximate monthly rent payment of a house
def getHousePayment(house):
    
    # split house string into parseable values
    houseData = house.split(",")
    
    # assign variables from houseData
    houseValue = int(houseData[1])
    
    # rent payments should be about 1% of a houses apraised value
    housePayment = houseValue/100
    
    return housePayment;


# determines if a house would be in the price range for a user
def isMatch(userIncome, housePayment):
    
    #convert user's yearly income into monthly income
    userIncome = userIncome / 12
    
    # assume a user is willing to pay 50% of their income for a house
    userMaxPayment = userIncome/2
    
    # assument the user wont want to live in a house with a payment below 30% of their income
    userMinPayment = userIncome/3
    
    
    # if the house payment is within the desinated range, return true, otherwise false
    if housePayment > userMinPayment and housePayment < userMaxPayment :
        return True;
    else:
        return False;
    
# returns the data of a house and its matched users
def getHouseMatchData(fileName):
    # open the file
    houseMatchFile = open(fileName, "r");
    
    # get the lines from the file
    lines = houseMatchFile.readlines();
    
    #create a variable to return
    houseMatchData = []
    curHouseValue = 0
    # loop through lines 
    for x in range(len(lines)):
        #get the currernt line
        line = lines[x]
        
        #if the line is a house store the value
        if line[0] != "\t":
            
            houseData = line.split(",")
            curHouseValue = int(houseData[1])
            
        else: #if the line is a matched user store the value in houseMatchData with the house value
            
            userData = line.split(",")
            houseMatchData.append([curHouseValue, int(userData[1]), int(userData[2])])
            
    
    # close houseMatchFile
    houseMatchFile.close();
    
    return houseMatchData

# outputs a graph relateing user age and education level for each house
def graph(dataFrame, labels):
    
    import plotly.express as px
    
    figure = px.parallel_coordinates(dataFrame, color="house price", dimensions = labels)
    
    figure.show(renderer="browser")
    
#======================================
# loop through users and hosues

# get users and houses from files
userList = open("UserData.txt", "r")
houseList = open("houseMarket.txt", "r")

userList = userList.readlines()
houseList = houseList.readlines()

# create a file to hold matches
matchList = open("matchList.txt", "w")

# loop through houses
for x in range(len(houseList)):
    
    # get curent house
    curHouse = houseList[x]
    
    # write current house to matchList
    matchList.write(curHouse)
    
    # loop through users
    for y in range(len(userList)):
        
        # get current user
        curUser = userList[y]

        # get aproximate income of the user
        userIncome = estimateIncome(curUser)

        # determines the aproximate rent of the house
        housePayment = getHousePayment(curHouse)
        
        # determine if the house is a match (save to file if true)
        if isMatch(userIncome, housePayment):
            matchList.write("\t" + curUser)

matchList.close()

# make a graph using the data stored in "matchList.txt" to represent how house price relates to age and education level
data = getHouseMatchData("matchList.txt");

dataFrame = pandas.DataFrame(data, columns = ["house price", "age", "education"])

graph(dataFrame, ["house price", "age", "education"])
