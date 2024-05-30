import React, { useState } from 'react';
import axios from 'axios';
import Profile from '../Profile/Profile';

const MyAccount = ({userId}) => {


  return (
      <>
        <Profile userId={userId}/>
      </>
  );
};

export default MyAccount;
