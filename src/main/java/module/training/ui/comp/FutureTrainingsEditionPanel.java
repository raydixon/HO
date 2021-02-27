package module.training.ui.comp;

import core.datatype.CBItem;
import core.gui.RefreshManager;
import core.model.HOVerwaltung;
import core.model.constants.TrainingConstants;
import core.training.TrainingPerWeek;
import core.util.Helper;
import module.training.ui.model.FutureTrainingsTableModel;
import module.training.ui.model.TrainingModel;
import java.awt.*;
import static module.lineup.LineupPanel.TITLE_FG;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


/**
 * Panel for editing selected future training week
 */
public class FutureTrainingsEditionPanel extends JPanel {

	private FutureTrainingsTableModel m_FutureTrainingsTableModel;
    private JComboBox m_jcbIntensity;
    private JComboBox m_jcbStaminaTrainingPart;
    private JComboBox m_jcbTrainingType;
    private JComboBox m_jcbCoachSkillEditor;
    private JComboBox m_jcbAssitantsTotalLevelEditor;

    private final TrainingModel m_TrainingModel;


    public FutureTrainingsEditionPanel(TrainingModel _TrainingModel, FutureTrainingsTableModel fm) {
        setLayout(new BorderLayout());
        this.m_TrainingModel = _TrainingModel;
        m_FutureTrainingsTableModel = fm;
        initComponents();



    }

    /**
     * Populate the Future training table with the future training
     */
    protected void resetFutureTrainings() {
        List<TrainingPerWeek> futureTrainingsToSave = new ArrayList<TrainingPerWeek>();

        for (TrainingPerWeek train: this.m_TrainingModel.getFutureTrainings()) {
            train.setTrainingIntensity(m_jcbIntensity.getSelectedIndex());
            train.setStaminaPart(m_jcbStaminaTrainingPart.getSelectedIndex() + 10);
            train.setTrainingType(((CBItem) m_jcbTrainingType.getSelectedItem()).getId());
            futureTrainingsToSave.add(train);
        }

        this.m_TrainingModel.saveFutureTrainings(futureTrainingsToSave);
        m_FutureTrainingsTableModel.populate(this.m_TrainingModel.getFutureTrainings());
        RefreshManager.instance().doRefresh();
    }

    /**
     * Initializes the state of this instance.
     */
    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel jlTrainingType = new JLabel(Helper.getTranslation("ls.team.trainingtype.short"));
        customizeLabel(jlTrainingType);
        jlTrainingType.setToolTipText(Helper.getTranslation("ls.team.trainingtype"));
        add(jlTrainingType, gbc);

        m_jcbTrainingType = new TrainingComboBox(true);
        m_jcbTrainingType.setToolTipText(Helper.getTranslation("ls.team.trainingtype"));
        gbc.gridy = 1;
        add(m_jcbTrainingType, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel jlTrainingIntensity = new JLabel(Helper.getTranslation("ls.team.trainingintensity.short"));
        customizeLabel(jlTrainingIntensity);
        jlTrainingIntensity.setToolTipText(Helper.getTranslation("ls.team.trainingintensity"));
        add(jlTrainingIntensity, gbc);

        m_jcbIntensity = new trainingParametersEditor(TrainingConstants.MIN_TRAINING_INTENSITY);
        m_jcbIntensity.setToolTipText(Helper.getTranslation("ls.team.trainingintensity"));
        gbc.gridy = 1;
        add(m_jcbIntensity, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel jlStaminatrainingshare = new JLabel(Helper.getTranslation("ls.team.staminatrainingshare.short"));
        customizeLabel(jlStaminatrainingshare);
        jlStaminatrainingshare.setToolTipText(Helper.getTranslation("ls.team.staminatrainingshare"));
        add(jlStaminatrainingshare, gbc);

        m_jcbStaminaTrainingPart = new trainingParametersEditor(TrainingConstants.MIN_STAMINA_SHARE);
        m_jcbStaminaTrainingPart.setToolTipText(Helper.getTranslation("ls.team.staminatrainingshare"));
        gbc.gridy = 1;
        add(m_jcbStaminaTrainingPart, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        JLabel jlCoachingSkill = new JLabel(Helper.getTranslation("ls.team.coachingskill.short"));
        customizeLabel(jlCoachingSkill);
        jlCoachingSkill.setToolTipText(Helper.getTranslation("ls.team.coachingskill"));
        add(jlCoachingSkill, gbc);

        m_jcbCoachSkillEditor  = new trainingParametersEditor(TrainingConstants.MIN_ASSISTANTS_COACH_LEVEL, TrainingConstants.MAX_ASSISTANTS_COACH_LEVEL);
        m_jcbCoachSkillEditor.setToolTipText(Helper.getTranslation("ls.team.coachingskill"));
        gbc.gridy = 1;
        add(m_jcbCoachSkillEditor, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        JLabel jlAssistantsTrainerLevel = new JLabel(Helper.getTranslation("ls.module.statistics.club.assistant_trainers_level.short"));
        customizeLabel(jlAssistantsTrainerLevel);
        jlAssistantsTrainerLevel.setToolTipText(Helper.getTranslation("ls.module.statistics.club.assistant_trainers_level"));
        add(jlAssistantsTrainerLevel, gbc);

        m_jcbAssitantsTotalLevelEditor  = new trainingParametersEditor(TrainingConstants.MIN_ASSISTANTS_COACH_LEVEL, TrainingConstants.MAX_ASSISTANTS_COACH_LEVEL);
        m_jcbAssitantsTotalLevelEditor.setToolTipText(Helper.getTranslation("ls.module.statistics.club.assistant_trainers_level"));
        gbc.gridy = 1;
        add(m_jcbAssitantsTotalLevelEditor, gbc);


        JButton button = new JButton(HOVerwaltung.instance().getLanguageString("ls.button.apply"));
        button.addActionListener(arg0 -> resetFutureTrainings());
        gbc.gridx = 5;
        add(button, gbc);

    }

    private void customizeLabel(JLabel jlabel){
        jlabel.setForeground(TITLE_FG);
        jlabel.setFont(getFont().deriveFont(Font.BOLD));
        jlabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
