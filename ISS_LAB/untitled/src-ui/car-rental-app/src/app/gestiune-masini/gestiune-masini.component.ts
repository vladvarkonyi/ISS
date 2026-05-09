import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MasinaService } from '../service/masina.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gestiune-masini',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './gestiune-masini.component.html',
  styleUrls: ['./gestiune-masini.component.css']
})
export class GestiuneMasiniComponent implements OnInit {
  masini: any[] = [];
  masinaForm: FormGroup;
  editMode = false;
  currentMasinaId: number | null = null;

  constructor(private fb: FormBuilder, private masinaService: MasinaService) {
    this.masinaForm = this.fb.group({
      marca: ['', Validators.required],
      model: ['', Validators.required],
      an: ['', [Validators.required, Validators.pattern('^[0-9]{4}$')]],
      pretPeZi: ['', [Validators.required, Validators.min(1)]],
      imagineUrl: [''],
      statusDisponibilitate: ['Disponibil', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadMasini();
  }

  loadMasini(): void {
    this.masinaService.getToateMasinile().subscribe({
      next: (data) => this.masini = data,
      error: (err) => console.error('Eroare la incarcarea masinilor', err)
    });
  }

  onSubmit(): void {
    if (this.masinaForm.valid) {
      if (this.editMode && this.currentMasinaId !== null) {
        this.masinaService.updateMasina(this.currentMasinaId, this.masinaForm.value).subscribe({
          next: () => {
            alert('Masina actualizata cu succes!');
            this.loadMasini();
            this.resetForm();
          },
          error: (err) => {
            console.error('Eroare la actualizarea masinii:', err);
            alert('Eroare la actualizarea masinii.');
          }
        });
      } else {
        this.masinaService.addMasina(this.masinaForm.value).subscribe({
          next: () => {
            alert('Masina adaugata cu succes!');
            this.loadMasini();
            this.resetForm();
          },
          error: (err) => {
            console.error('Eroare la adaugarea masinii:', err);
            alert('Eroare la adaugarea masinii.');
          }
        });
      }
    }
  }

  editMasina(masina: any): void {
    this.editMode = true;
    this.currentMasinaId = masina.id;
    this.masinaForm.patchValue({
      marca: masina.marca,
      model: masina.model,
      an: masina.an,
      pretPeZi: masina.pretPeZi,
      imagineUrl: masina.imagineUrl,
      statusDisponibilitate: masina.statusDisponibilitate || 'Disponibil'
    });
  }

  deleteMasina(id: number): void {
    if (confirm('Sunteti sigur ca doriti sa stergeti aceasta masina?')) {
      this.masinaService.deleteMasina(id).subscribe({
        next: () => {
          alert('Masina stearsa cu succes!');
          this.loadMasini();
        },
        error: (err) => {
          console.error('Eroare la stergerea masinii:', err);
          alert('Eroare la stergerea masinii.');
        }
      });
    }
  }

  resetForm(): void {
    this.editMode = false;
    this.currentMasinaId = null;
    this.masinaForm.reset({ statusDisponibilitate: 'Disponibil' });
  }
}
