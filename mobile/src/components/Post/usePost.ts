import { useState } from "react";

export const usePost = () => {
  const [modal, setModal] = useState(false);

  const handleModal = () => setModal(!modal);

  return { handleModal, modal };
};
