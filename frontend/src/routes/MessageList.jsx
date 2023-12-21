import { useEffect, useState } from "react";
import { Typography, Button } from "@mui/material";
import { Link } from "react-router-dom";

import { getAllMessages } from "../services/message";

export default function MessageList() {
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    getAllMessages().then((messages) => {
      setMessages(messages);
    });
  }, []);

  return (
    <>
      <Typography variant="h4" component="h1">
        Messages
      </Typography>
      <ul>
        {messages.map((message) => (
          <li key={message.id}>{message.content}</li>
        ))}
      </ul>
      <Button
        component={Link}
        to="/messages/add"
        variant="contained"
        sx={{ marginBottom: 2 }}
      >
        Add a message
      </Button>
    </>
  );
}
