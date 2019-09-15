import React from 'react';
import ReactTooltip from 'react-tooltip';
import marker from './img/marker.png';

const StorePin = props => {
    console.log(props);
    return (
        <>
            <div data-tip data-for={props.item}>
                <img src={marker} height="25px" width="25px" alt={props.text}></img>
            </div>

            <ReactTooltip id={props.item} type='info' effect='solid'>
                <div>{props.text}</div>
                <div>{props.open === 'Gesloten' ? "Store Closed" : 'Opens ' + props.open}</div>
                <div>{props.close === 'Gesloten' ? '' : 'Closes ' + props.close}</div>
            </ReactTooltip>

        </>
    )
}

export default StorePin;
