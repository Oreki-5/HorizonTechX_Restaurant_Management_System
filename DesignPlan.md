Restaurant Management System

Functional Requirements:

- Table reservations
- Tables availability check
- Placing Orders
- Generate Bills per table
- Managing menu
- Managing inventory
    - Bulk insert and update inventory data
    - auto-updating inventory
- Reporting features

Assumptions:

- Inventory will be updated on a daily basis
- Bulk updating inventory will be like uploading a datafile of physical
inventory check at EOD and API will handle updating all records in the
Database.

- For inventory auto-update, we assume that, whenever a menu item is
ordered, the ingredients required for that item will be auto subtracted from
the inventory.

- For practical reasons we will allow an option for ordering of menuItems
even if ingredients are not in stock from a Data perspective, cause
sometimes there may be some available stock not recorded in the database.
- If any ingredient is not available for a menuitem to order, it should throw
a warning saying there is a lack of ingredient.

- Reservation will be an independent to orders.
- Orders will be taken by table number.

- If an order is updated, we assume that the ingredients for previous order
are already used so we don't restore item's quantity in the inventory

- Payments service will be outside of the scope for this project.

