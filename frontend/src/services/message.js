const BACKEND_URL = import.meta.env.VITE_BACKEND_URL ?? "";

export function getAllMessages() {
  return fetch(`${BACKEND_URL}/api/messages`).then((response) =>
    response.json()
  );
}

export function createMessage(message) {
  return fetch(`${BACKEND_URL}/api/messages`, {
    method: "post",
    body: JSON.stringify(message),
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  }).then((response) => {
    if (!response.ok) {
      throw new Error("Failed to create the message");
    }

    return response.json();
  });
}
