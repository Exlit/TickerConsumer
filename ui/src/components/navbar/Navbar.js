import React, {useState} from 'react';
import {FaBars, FaTimes} from 'react-icons/fa';
import { Nav, NavbarContainer, NavLogo, MobileIcon, NavMenu, NavItem, NavLinks} from './Navbar.elements';
import {IconContext} from 'react-icons/lib';

const Navbar = () => {
    const [click, setClick] = useState(false)

    const handleClick = () => setClick(!click)


    return (
        <IconContext.Provider value={{color: '#fff'}}>
                <Nav>
            <NavbarContainer>
                <NavLogo to="/">
                   100DevTest
               </NavLogo>
                <MobileIcon onClick={handleClick}>
                   {click ? <FaTimes /> : <FaBars />}
                </MobileIcon>
                <NavMenu onClick={handleClick} click={click}>
                    <NavItem>
                        <NavLinks to='/'>
                            Home
                        </NavLinks>
                    </NavItem>
                </NavMenu>
            </NavbarContainer>
        </Nav>
        </IconContext.Provider>
    )
}

export default Navbar
