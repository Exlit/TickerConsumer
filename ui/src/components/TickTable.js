import * as React from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

function Row(props) {
  return (
    <React.Fragment>
      <TableRow
              hover={true}
              selected={true}
              key={props.row.fileName}
            >
              <TableCell component="th" scope="row">
                {props.row.currencyPair}
              </TableCell>
              <TableCell align="left">{props.row.currentBuyPrice}</TableCell>
              <TableCell align="left">{props.row.currentSellPrice}</TableCell>
              <TableCell align="left">{props.row.dailyPercentage}</TableCell>
              <TableCell align="left">{props.row.weeklyPercentage}</TableCell>
              <TableCell align="left">{props.row.monthlyPercentage}</TableCell>
            </TableRow>
    </React.Fragment>
  )}

export default function TickTable(props) {
  return (
    <TableContainer style={{width: 1280, maxHeight: 1000}}>
      <Table 
      sx={{ minWidth: 650}} 
      size="small" 
      aria-label="a dense table"
      >
        <TableHead>
          <TableRow>
            <TableCell>Currency pair</TableCell>
            <TableCell align="left">Buy</TableCell>
            <TableCell align="left">Sell</TableCell>
            <TableCell>Day change</TableCell>
            <TableCell align="left">Week change</TableCell>
            <TableCell align="left">Month change</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.data.map((row) => (
            <Row key={row.currencyPair} row={row} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
