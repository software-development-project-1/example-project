import { useState } from "react";
import { Typography, Button, Box, TextField } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";

import { createMessage } from "../services/message";

export default function AddMessage() {
  const navigate = useNavigate();
  const [content, setContent] = useState("");

  function handleSubmitMessage(event) {
    event.preventDefault();

    createMessage({ content }).then(() => {
      navigate("/");
    });
  }

  return (
    <>
      <Typography variant="h4" component="h1" sx={{ marginBottom: 2 }}>
        Add a message
      </Typography>
      <form onSubmit={handleSubmitMessage}>
        <TextField
          label="Content"
          variant="outlined"
          value={content}
          onChange={(event) => setContent(event.target.value)}
          required
          multiline
          fullWidth
        />

        <Box sx={{ marginTop: 2 }}>
          <Button type="submit" variant="contained" sx={{ marginRight: 1 }}>
            Add
          </Button>
          <Button component={Link} to="/">
            Cancel
          </Button>
        </Box>
      </form>
    </>
  );
}
