Barbershop Simulation (Java)

This project is a simple Barbershop Simulation written in Java.  
It models a barber shop with a limited number of chairs where VIP clients and ordinary clients (ORD)  arrive randomly. The barber finishes clients one by one, and if the shop is full, new arrivals leave immediately.



Features
- Simulation runs step by step when the user presses Enter.
- A random number x between 0 and 3 decides the event:
  - x = 0 → -- ClientName → The barber finishes trimming the current client.  
  - x = 1 → ++ VIPi → A VIP client arrives and takes priority seating.  
  - x = 2 or 3 → ++ ORDi → An ordinary client arrives and takes the first available chair.  
  - If the shop is full and a new client arrives, the event shows as +- ClientName (client rejected).  
- Shop state is displayed after each event.



