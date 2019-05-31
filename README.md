This is a task that I have been asked to do as a demonstration of my coding skills during a job interview by a certain company.

TASK:

Write a java program that given a name in input checks in EN Wikipedia API all the different pages that are present for that person. For each page, try to extract the year of birth, if present, from the response (if there is a year of birth we assume the page is about a person). If so return a list of “page title, page id, date of birth” for each result.

Input: 2 strings: “First” “Last”.

EG: First:“Igor” Last:“Makarov”

Output a list of “Wikipedia entities” each with "Name and year of birth"

API CALL:

https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srsearch=%22Igor+Makarov%22&srnamespace=0&srlimit=30&prop=categories&srprop=categorysnippet%7Cwordcount%7Ctimestamp%7Csnippet&redirects=false


1. Extra points for showcasing the use of Maven
2. Extra points for initializing classed / injecting through spring framework (Not boot - you can use boot if you want but I want to see some spring things).
