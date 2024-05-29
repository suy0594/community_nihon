import React from 'react';
import { useNavigate } from 'react-router-dom';

const GroupButton = () => {
    const navigate = useNavigate();
    return (
        <>
        <button onClick={() => {navigate('group');}}>Go to the Group</button>
        </>
    );
};
export default GroupButton;