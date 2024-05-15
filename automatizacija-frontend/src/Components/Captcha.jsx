import React from 'react'
import {TextField} from './TextField';
import { IoRefreshCircleSharp } from "react-icons/io5";
import '../Components/captcha.css';

export const Captcha = ({captcha, refreshString}) => {
    
  return (
    <div>
        <TextField label="Captcha text" name="captcha" type="text" style={{textIndent: '86px'}}/>
        <div className='captcha'>
            <span style={{textDecoration: 'line-through', userSelect: 'none', textShadow: '0 0 9px blue'}}>{captcha}</span>
            <button
                type="button"
                style={{background: 'transparent', border: 'none', display: 'contest', marginLeft: '5px'}}
                onClick={() => refreshString()}
            >
                <IoRefreshCircleSharp style={{color: 'blue'}}/>
            </button>
        </div>
    </div>
  )
}