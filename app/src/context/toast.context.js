import { useCallback, useContext, useState, createContext } from 'react';
import { Toast } from '../ui';

const ToastContext = createContext();

export default ToastContext;

export function ToastContextProvider({ children }) {
  const [toasts, setToasts] = useState([]);

  const addToast = useCallback(
    function (toast) {
      setToasts((toasts) => [...toasts, toast]);
      setTimeout(() => setToasts((toasts) => toasts.slice(1)), 5000);
    },
    [setToasts]
  );

  return (
    <ToastContext.Provider value={addToast}>
      {children}
      <div className="toasts-wrapper">
        {toasts.map((toast, index) => (
          <Toast text={toast} key={index} />
        ))}
      </div>
    </ToastContext.Provider>
  );
}

export function useToastContext() {
  return useContext(ToastContext);
}
