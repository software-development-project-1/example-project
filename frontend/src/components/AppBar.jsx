import { useEffect, useState } from "react";

import {
  AppBar as MuiAppBar,
  Box,
  Toolbar,
  Typography,
  IconButton,
  Drawer,
  List,
  ListItem,
  ListItemButton,
  ListItemText,
} from "@mui/material";

import MenuIcon from "@mui/icons-material/Menu";
import { Link, useLocation } from "react-router-dom";

export default function AppBar() {
  const location = useLocation();
  const [drawerOpen, setDrawerOpen] = useState(false);

  useEffect(() => {
    setDrawerOpen(false);
  }, [location]);

  return (
    <>
      <Drawer
        anchor="left"
        open={drawerOpen}
        onClose={() => setDrawerOpen(false)}
      >
        <List sx={{ minWidth: 250 }}>
          <ListItem disablePadding>
            <ListItemButton component={Link} to="/">
              <ListItemText primary="Messages" />
            </ListItemButton>
          </ListItem>
        </List>
      </Drawer>
      <Box sx={{ flexGrow: 1 }}>
        <MuiAppBar position="static">
          <Toolbar>
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              onClick={() => setDrawerOpen(true)}
              sx={{ mr: 2 }}
            >
              <MenuIcon />
            </IconButton>
            <Typography
              variant="h6"
              component={Link}
              color="inherit"
              to="/"
              sx={{ flexGrow: 1, textDecoration: "none" }}
            >
              Messages
            </Typography>
          </Toolbar>
        </MuiAppBar>
      </Box>
    </>
  );
}
