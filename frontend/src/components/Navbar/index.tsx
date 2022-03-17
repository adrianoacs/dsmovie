import {ReactComponent as GithubIcon} from 'assets/img/gitHub.svg';
import "./styles.css"

function Navbar(){
    return (
        <header>
            <nav className='container'>
                <div className='dsmovie-nav-content'>
                    <h1>DSMovie</h1>
                    <a href="https://github.com/adrianoacs" className='dsmovie-contact-container'>
                        <GithubIcon />
                        <p className='dsmovie-contact-link'>/devsuperior</p>
                    </a>
                </div>
            </nav>
        </header>
    );
}

export default Navbar;