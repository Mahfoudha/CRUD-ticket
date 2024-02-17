from flask import Flask, render_template, request, redirect, url_for
import zeep

app = Flask(__name__)
wsdl = 'http://localhost:8080/ticketservice?wsdl'  # URL de votre service SOAP Ticket
client = zeep.Client(wsdl=wsdl)

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'POST':
        id = request.form.get('id')
        nomFilm = request.form.get('nomFilm')
        date = request.form.get('date')
        heure = request.form.get('heure')
        salle = request.form.get('salle')
        prix = request.form.get('prix')
        
        ticket_data = {
            'id': id,
            'nomFilm': nomFilm,
            'date': date,
            'heure': heure,
            'salle': salle,
            'prix': prix
        }
        
        client.service.ajouterTicket(ticket_data)

    all_tickets = client.service.listerTickets()
    return render_template('index.html', tickets=all_tickets)

@app.route('/update_ticket/<int:id>', methods=['POST'])
def update_ticket(id):
    nomFilm = request.form.get('new_nomFilm')
    date = request.form.get('new_date')
    heure = request.form.get('new_heure')
    salle = request.form.get('new_salle')
    prix = request.form.get('new_prix')
    
    new_ticket_data = {
        'id': id,
        'nomFilm': nomFilm,
        'date': date,
        'heure': heure,
        'salle': salle,
        'prix': prix
    }
    
    client.service.modifierTicket(new_ticket_data)
    return redirect(url_for('index'))

@app.route('/delete_ticket/<int:id>', methods=['POST'])
def delete_ticket(id):
    client.service.supprimerTicket(id)
    return redirect(url_for('index'))

@app.route('/add_ticket')
def add_ticket():
    return render_template('form_ticket.html')

@app.route('/list_tickets')
def list_tickets():
    all_tickets = client.service.listerTickets()
    return render_template('list_tickets.html', tickets=all_tickets)

if __name__ == '__main__':
    app.run(debug=True)
