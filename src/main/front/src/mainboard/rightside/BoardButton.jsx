import React from 'react';
import { useNavigate } from 'react-router-dom';

const BoardButton = () => {
    const navigate = useNavigate();
    return (
        <>
        <button onClick={() => {navigate('createPost');}}>Create Post</button>
        </>
    );
};
export default BoardButton;