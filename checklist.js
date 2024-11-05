import React, { useState } from 'react';

function Checklist() {
    const [checklistName, setChecklistName] = useState("");
    const [category, setCategory] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await fetch("/api/checklists", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name: checklistName, category })
        });
        const result = await response.json();
        console.log("Checklist Created", result);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={checklistName}
                onChange={(e) => setChecklistName(e.target.value)}
                placeholder="Checklist Name"
            />
            <input
                type="text"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
                placeholder="Category"
            />
            <button type="submit">Create Checklist</button>
        </form>
    );
}

export default Checklist;
