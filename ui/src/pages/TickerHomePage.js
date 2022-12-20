import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Button from '@material-ui/core/Button';
import styled from 'styled-components';
import Table from '../components/TickTable';

const ButtonContainer = styled.div`
  padding-top: 10px;
  flex: 1;
  display: flex;
  justify-content: center;
`;

const TickerHomePage = () => {
const [data, setData] = useState([]);

useEffect(() => {getTicks()}, []);

const getTicks = () => {
axios.get("http://localhost:8080/getTicks")
.then(function (response) {
  setData(response.data.tickerDtoList)
  console.log(response.data.tickerDtoList)
})
}
const Refresh = () => 
<ButtonContainer>
<Button 
variant="contained" color="primary"
onClick={() => {getTicks()}}
>
  refresh
  </Button> 
  </ButtonContainer>
  return (
    <div>
      <Refresh />
      <Table data={data}/>
    </div>
  )
}

export default TickerHomePage