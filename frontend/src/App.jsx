import {
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";

import { CssBaseline } from "@mui/material";

import Root from "./routes/Root";
import MessageList from "./routes/MessageList";
import AddMessage from "./routes/AddMessage";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<Root />}>
      <Route index element={<MessageList />} />
      <Route path="messages/add" element={<AddMessage />} />
    </Route>
  )
);

function App() {
  return (
    <>
      <CssBaseline />
      <RouterProvider router={router} />
    </>
  );
}

export default App;
