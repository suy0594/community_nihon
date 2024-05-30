import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';

const CreateGroupButton = () => {
    const buttonstyle = {
        display: 'block',
        textAlign: 'center',
        verticalAlign: 'middle',
        textDecoration: 'none',
        position: 'relative',
        margin: 'auto',
        padding: '0.5rem 0.5rem',
        fontWeight: 'bold',
        borderRadius: '10px',
        color: '#27acd9',
        border: '1px solid #27acd9',
        boxShadow: '3px 1px #27acd9',
        transition: '0.3s ease-in-out',
      };
      const hoverOnstyle = {
        boxShadow: 'none',
        transform: 'translate(2px, 2px)',
        color: '#27acd9',
      };
    
        const [BtnStyle, setBtnStyle] = useState(buttonstyle);
        const navigate = useNavigate();
    
        const onBtnHover = () => {
            setBtnStyle({ ...buttonstyle, ...hoverOnstyle });
          };
        
          const offBtnHover = () => {
            setBtnStyle(buttonstyle);
          };
        return (
            <>
            <button style={BtnStyle}
            onMouseEnter={onBtnHover}
            onMouseLeave={offBtnHover}
             onClick={() => {navigate('createGroup');}}>
                Create Group
                </button>
            </>
        );
};
export default CreateGroupButton;