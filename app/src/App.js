import { ToastContextProvider } from './context/toast.context';
import { RouterProvider } from 'react-router-dom';
import { GlobalUserProvider } from './context/user.context';
import { router } from './router/index';

function App() {
  return (
    <ToastContextProvider>
      <GlobalUserProvider>
        <RouterProvider router={router} />
      </GlobalUserProvider>
    </ToastContextProvider>
  );
}

export default App;
