import { createBrowserRouter } from 'react-router-dom';
import {
  ChangePassScreen,
  HomeScreen,
  Profile,
  Login,
} from '../ui/index';
import { PrivateRoute } from './privateRoute';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Login />,
  },
  {
    path: '/home',
    element: <PrivateRoute Screen={HomeScreen} />,
  },
  {
    path: '/usuarios/alterar-senha/:id/:token',
    element: <ChangePassScreen />,
  },
  {
    path: '/profile',
    element: <PrivateRoute Screen={Profile} />,
  },
]);
