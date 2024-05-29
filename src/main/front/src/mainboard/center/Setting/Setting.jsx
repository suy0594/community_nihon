import React, { useState } from 'react';
import axios from 'axios';

const Setting = ({Logout}) => {


  return (
      <>
        <p>This is Setting page.</p>
        <button onClick={Logout}>Logout</button>
      </>
  );
};

export default Setting;
