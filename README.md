# guestList
 Manages a guest list and a waiting list for an event.
 /****************************************************************************************************
 
   This program allows the user to choose the number of seats that will be available for the event and can store guests by their name, last name, email and phone.
   If the number of available spots is 0 this means the guestList is full and the following guests that want to attend the event will be automatically registered in order in    the waitingList untill this list is full too.
   As a help for the user the application offers a menu with all his available functions and a little documentation for an much easier understanding of what each function does.
   
   ******************************************************************************************************************************************************/
   
  The program has 3 classes: Guest, GuestList, Test.
           
           > class Guest: contains class-specific methods and defines a guest through following fields: last name/ first name/ email/ phone number;
           
           > class GuestList: This class will keep track of registrations for an event
           
           > class Test:  The main class of the application, responsible for the interaction with the user.
                          This will implement the input processing functionality given to the application, guiding the user to enter the input commands correctly, displaying the available command
                          
 THE PROGRAM HAS NEXT FUNCTIONS(CAN DO):
 
       help         -> Renders a list of commands(menu);
           
       add          -> Registers a guest by name,last name, email, phone number;
           
       check        -> Checks if a guest is already on the list;
                        -> User can check by choosing between 3 criteria: Name and Last Name/ Email/ Phone Number;
                        
       remove       -> Removes a guest if it is on the list or waiting list;
                        -> User can remove guest by choosing between 3 criteria: Name and Last Name/ Email/ Phone Number;
                        
       update       -> Updates guest credentials and uses same mechanism as check and remove to find guest to update(based on 3 criteria: Name and Last Name/ Email/ Phone                                                                                                                                                                         Number);
                    -> After the guest is found, user can choose what field to update(last name, first name, email or phone number);
                    
       guests       -> Shows the participants registered for the event;
       
       waitlist     -> Shows the participants registered in waitinglist;
       
       available    -> Shows the number of spots available at the event;
       
       guests_no    -> Shows total number of people registered/that will attend the event;
       
       waitlist_no  -> Shows number of persons registered on waiting list;
       
       subscribe_no -> Shows total number of participants for the event;
       
       search       -> Partial search, searches a guest using a substring;
                    -> EX: Alexandru Butnaru = guest at the event
                           search by  "ale"  and get guest credentials;
                           
       quit         -> Closes the application;        
                          
