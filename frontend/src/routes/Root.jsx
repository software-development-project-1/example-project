import { Outlet } from "react-router-dom";
import { Container } from "@mui/material";

import AppBar from "../components/AppBar";

export default function Root() {
  return (
    <>
      <AppBar />
      <Container sx={{ marginY: 4 }}>
        <Outlet />
      </Container>
    </>
  );
}
