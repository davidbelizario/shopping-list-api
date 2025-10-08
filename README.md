## 🚀 How to Run

1. Make sure Docker is installed on your machine.

2. Open a terminal and navigate to the project's root folder.

3. Build the Docker image with the following command: "docker build -t shopping-list-api ."

4. Run the application: "docker run -p 8080:8080 shopping-list-api"

## 📌 Available Endpoints

| Method | Endpoint        | Description                  |
|--------|----------------|------------------------------|
| GET    | /items         | List all items               |
| POST   | /items         | Create a new item            |
| GET    | /items/{id}    | Get item by ID               |
| PUT    | /items/{id}    | Update an item by ID         |
| DELETE | /items/{id}    | Delete an item by ID         |

### POST /items
### PUT /items

**Request Body (JSON):**

```json
{
  "name": "Example Item",
  "price": 5.50,
  "quantity": 2,
  "category": "any"
}