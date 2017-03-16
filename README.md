# ESA_Java

Analyzing efficiency of Java Implementations for Enhanced Suffix Arrays + Applications

- ESA is built with the SAIS implementation in O(n)

  - LCP is built in O(n) with discriminating characters //TODO space efficiently compute discriminating characters
  - BWT is built in O(n) with a byte array representing the OCC function used for computing the FM-Index
  - Child Table is built in O(n) with either
    - directly compute the Child Table
    - compute UP, DOWN, NEXT in order to calculate the Child Table
  - Maximal and Supermaximal Repeats are built in O(n)
    - Maximal Repeats make use of LCP-Array
    - Supermaximal Repeats make use of pairwise distinct BWT Characters for specific positions and LCP-Array
  - RMQ is built in O(n log(n))
  
- Applications

  - Binary Search: //TODO validate
  - Find Query of length m using LCP and Child Table in O(|$ \sum| * m)
    
    
 
