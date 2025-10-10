const username = "paolo";
const API_URL = `http://localhost:8080/users/${username}/todos`;

const form = document.getElementById("todo-form");
const tableBody = document.querySelector("#todoTable tbody");

// READ all todos
async function fetchTodos() {
    try {
        const res = await fetch(API_URL);
        const todos = await res.json();
        tableBody.innerHTML = "";
        todos.forEach(todo => addTodoRow(todo));
    } 
        catch (err) {
        console.error("Failed to fetch todos:", err);
  }
}

// CREATE or UPDATE
form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const todo = 
    {
        username: username,
        description: document.getElementById("description").value,
        targetDate: document.getElementById("due-date").value,
        done: document.getElementById("done").checked
    };

    const id = document.getElementById("todo-id").value;

    try {
        if (id) 
        {
            // UPDATE
            await fetch(`${API_URL}/${id}`, 
            {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(todo)
            });
        } 
        else 
        {
            // CREATE
            await fetch(API_URL, 
            {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(todo)
            });
        }

        form.reset();
        document.getElementById("todo-id").value = "";
        await fetchTodos();
        alert("✅ Todo saved successfully!");

    } 
    catch (err) 
    {
        console.error("Save failed:", err);
        alert("Could not save the todo. See console for details.");
    }
});

// DISPLAY each row (create button listeners instead of inline onclick)
function addTodoRow(todo) {
    const tr = document.createElement("tr");

    const descTd = document.createElement("td");
    descTd.textContent = todo.description || "";

    const dateTd = document.createElement("td");
    dateTd.textContent = todo.targetDate || "";

    const statusTd = document.createElement("td");
    statusTd.textContent = todo.done ? "✅ Done" : "❌ Pending";

    const actionsTd = document.createElement("td");
    const editBtn = document.createElement("button");
    editBtn.textContent = "Edit";
    editBtn.classList.add("edit-btn");
    editBtn.addEventListener("click", () => editTodo(todo.id));

    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.classList.add("delete-btn");
    deleteBtn.addEventListener("click", () => deleteTodo(todo.id));


    actionsTd.appendChild(editBtn);
    actionsTd.appendChild(deleteBtn);

    tr.appendChild(descTd);
    tr.appendChild(dateTd);
    tr.appendChild(statusTd);
    tr.appendChild(actionsTd);

    tableBody.appendChild(tr);
}

// DELETE
async function deleteTodo(id) {
    if (!confirm("🗑️ Are you sure you want to delete this task?")) return;
    try 
    {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        await fetchTodos();
        alert("🗑️ Todo deleted successfully!");
    }   
    catch (err) 
    {
        console.error("Delete failed:", err);
    }
}

// EDIT
async function editTodo(id) {
    try 
    {
        const res = await fetch(`${API_URL}/${id}`);
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const todo = await res.json();

        document.getElementById("todo-id").value = todo.id;
        document.getElementById("description").value = todo.description || "";
        document.getElementById("due-date").value = todo.targetDate ? todo.targetDate.split("T")[0] : "";
        document.getElementById("done").checked = !!todo.done;

    } 
    catch (err) 
    {
        console.error("Fetch todo for edit failed:", err);
    }
}

fetchTodos();
