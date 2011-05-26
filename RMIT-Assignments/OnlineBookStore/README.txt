|--------------------------------------------------
|  Program name: Online Book Store
|  Author: Tran Tuan Anh
|  Student ID: s3269999
|  Institute: RMIT University
|  Year of creation: 2011
|--------------------------------------------------


********************************************************************************
* Some small changes have been made to make the old program adaptable to
* new web user interface.
********************************************************************************


*****************************************************************************************
* INSTRUCTION TO DEPLOY AND EXECUTE THE WHOLE APPLICATION:
*
* - The program should be opened with NetBeans IDE
* - In NetBeans IDE, this program should be run as a whole (by clicking Run on
*   the Enterprise Application OnlineBookStore), not as separate modules
* - The whole application should be deployed before the web module can run
* - In most case, lookup failure for ejb/BookStoreManager can be resolved by
*   changing the port in the line
*   <iiop-listener port="xxxx" id="orb-listener-1" address="0.0.0.0" lazy-init="true" />
*   of the file: "C:\glassfish-3.0.1\glassfish\domains\domain1\config\domain.xml" into
*   "3700".
*****************************************************************************************


********************************************************************************
* FEATURES THAT HAVE BEEN IMPLEMENTED:
*
* - Register.
* - Login.
* - Edit personal information.
* - Browse for books.
* - Search for books.
* - Pagination.
* - View book details.
* - Add rating and comment.
* - Add to cart.
* - View cart.
* - Checkout & confirm an order.
*
* + Bonus features:
*   - Pagination tag extending SimpleTagSupport.
*   - Book list tag extending BodyTagSupport.
********************************************************************************


********************************************************************************
* KNOWN LIMITATIONS
*
* - Errors cannot be displayed in each page, but in another error page
* - Resolution errors sometimes happen.
********************************************************************************