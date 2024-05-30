import React from 'react';
import { useNavigate } from 'react-router-dom';

const GroupButton = ({id}) => {
    const navigate = useNavigate();
    return (
        <>
        <button onClick={() => {navigate('${id}');}}>Go to the Group</button>
        </>
    );
};
export default GroupButton;